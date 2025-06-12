(ns banking-simulator.account
  (:require [clojure.spec.alpha :as s]))

; Purpose: To define account data structures and it's operations

; Need the following functions here:
; create-account
; deposit
; withdraw
; get-balance

(defn create-account [id initial-balance]
  {:id id :balance initial-balance})

(defn deposit [account amount] ; Our accounts are going to be 
  (update account :balance + amount))

(defn withdraw [account amount]
  (if (>= (:balance account) amount)
    (update account :balance - amount)
    (throw (ex-info "Insufficient funds" {:account account :amount amount}))))

