(ns banking-simulator.account)

(defn create-account [id initial-balance]
  {:id id :balance initial-balance})

(defn deposit [account amount]
  (update account :balance + amount))

(defn withdraw [account amount]
  (if (>= (:balance account) amount)
    (update account :balance - amount)
    (throw (ex-info "Insufficient funds" {:account account :amount amount}))))