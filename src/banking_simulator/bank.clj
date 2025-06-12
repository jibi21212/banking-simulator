(ns banking-simulator.bank
  (:require [banking-simulator.account as account]
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

(defn transfer [from-id to-id amount]
  (dosync
   ;; Transfer logic + Audit logic
   ))