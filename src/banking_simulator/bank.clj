(ns banking-simulator.bank
  (:require [banking-simulator.account :as account]
            [banking-simulator.audit :as audit]))

; Purpose: Manage overall bank state, coordinate operations, handle transfers

; Need the following functions:
; open-account
; close-account
; transfer
; get-account

; use STM (dosync ...) to coordinate the updates from accounts

(def accounts (ref {}))

(defn open-account [id initial-balance]
  (dosync
   (alter accounts assoc id (account/create-account id initial-balance))
   (audit/log-event {:type :account-created :id id :balance initial-balance})))


   ; Transfer logic + Audit logic:
   ; I assume to complete a transaction:
   ; I have to withdraw amount from from-id 
   ; Deposit amount into to-id
   ; Log that that amount has successfully transferred from-id to to-id

(defn transfer [from-id to-id amount]
  (dosync
   (let [from-account (get @accounts from-id)
         to-account   (get @accounts to-id)]
     (when (nil? from-account)
       (throw (ex-info "Source account does not exist" {:id from-id})))
     (when (nil? to-account)
       (throw (ex-info "Destination account does not exist" {:id to-id})))
     (when (< (:balance from-account) amount)
       (throw (ex-info "Insufficient funds" {:from-id from-id :balance (:balance from-account) :amount amount})))
     ;; Update both accounts atomically
     (alter accounts assoc from-id (account/withdraw from-account amount))
     (alter accounts assoc to-id   (account/deposit to-account amount))
     ;; Log the transfer (outside of STM for best practice, see below)
     ))
  ;; After transaction, log the event (outside dosync is safest)
  (audit/log-event (format "Transfer: %d FROM: %s TO: %s" amount from-id to-id)))