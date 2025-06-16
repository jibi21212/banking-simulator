(ns banking-simulator.core
  (:gen-class)
  (:require
   [banking-simulator.account :as account]
   [banking-simulator.audit :as audit]
   [banking-simulator.bank :as bank]))

(defn prompt [msg]
  (println msg)
  (flush)
  (read-line))

(defn -main [& args]
  (println "Welcome to the Bank Simulator!")
  (loop []
    (println "\nChoose an option:")
    (println "1. Create account")
    (println "2. Deposit")
    (println "3. Withdraw")
    (println "4. Transfer")
    (println "5. View Balance")
    (println "6. Show audit log")
    (println "7. Exit")
    (print "Enter choice: ") (flush)
    (let [choice (read-line)]
      (case choice
        "1" (let [id (prompt "Enter ID:")
                  initial-balance (Integer/parseInt (prompt "Enter initial deposit:"))]
              (try
                (bank/open-account id initial-balance)
                (println "Account created.")
                (catch Exception e
                  (println "Error creating account:" (.getMessage e))))
              (recur))
        "2" (let [id (prompt "Enter account ID:")
                  amount (Integer/parseInt (prompt "Enter deposit amount:"))]
              (try
                (account/deposit id amount)
                (println "Deposit successful.")
                (catch Exception e
                  (println "Error depositing:" (.getMessage e))))
              (recur))
        "3" (let [id (prompt "Enter account ID:")
                  amount (Integer/parseInt (prompt "Enter withdrawal amount:"))]
              (try
                (account/withdraw id amount)
                (println "Withdrawal successful.")
                (catch Exception e
                  (println "Error withdrawing:" (.getMessage e))))
              (recur))
        "4" (let [from-id (prompt "Enter your account ID:")
                  to-id (prompt "Enter account ID to transfer to:")
                  amount (Integer/parseInt (prompt "Enter amount:"))]
              (try
                (bank/transfer from-id to-id amount)
                (println "Transfer successful.")
                (catch Exception e
                  (println "Error transferring:" (.getMessage e))))
              (recur))
       "5" (let [account (prompt "Enter Account ID (-1 to view all):")]
             (if (= "-1" account)
               (try
                 (bank/all-account-balance)
                 (catch Exception e
                   (println "No Accounts available")))
               (try
                 (println (bank/single-account-balance account))
                 (catch Exception e
                   (println "Invalid Account ID" (.getMessage e)))))
             (recur))
        "6" (do
              (println "Audit log:")
              (doseq [entry @audit/audit-log]
                (println entry))
              (recur))
        "7" (println "Goodbye!")
        (do
          (println "Invalid choice.")
          (recur))))))