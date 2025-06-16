(ns banking-simulator.bank
  (:require [banking-simulator.account :as account]
            [banking-simulator.audit :as audit]))

(def accounts (ref {}))

(defn open-account [id initial-balance]
  (dosync
   (alter accounts assoc id (account/create-account id initial-balance))
   (audit/log-event (format "Account created: ID=%s, Initial Balance=%d" id initial-balance))))

(defn single-account-balance [acc]
  (when (not (contains? @accounts acc))
    (throw (ex-info "Account does not Exist" {:id acc})))
  (let [account (get @accounts acc)]
    (str "Account: " acc ", Balance: " (:balance account)))) 

(defn all-account-balance []
  (when (empty? @accounts)
    (throw (ex-info "Bank has no account Entries" {})))
  (doseq [[acc account] @accounts]
    (println (str "Account: " acc ", Balance: " (:balance account)))))

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
     (alter accounts assoc from-id (account/withdraw from-account amount))
     (alter accounts assoc to-id   (account/deposit to-account amount))))
  (audit/log-event (format "Transfer: %d FROM: %s TO: %s" amount from-id to-id))
  (audit/log-event (single-account-balance from-id))
  (audit/log-event (single-account-balance to-id)))

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
     (alter accounts assoc from-id (account/withdraw from-account amount))
     (alter accounts assoc to-id   (account/deposit to-account amount))))
  (audit/log-event (format "Transfer: %d FROM: %s TO: %s" amount from-id to-id)))