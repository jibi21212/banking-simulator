(ns banking-simulator.bank-test
  (:require [clojure.test :refer :all]
            [banking-simulator.bank :as bank]
            [banking-simulator.audit :as audit]))

(deftest open-account-test
  (with-redefs [bank/accounts (ref {})]
    (bank/open-account "acc1" 100)
    (is (= {:id "acc1" :balance 100} (get @bank/accounts "acc1")))))

(deftest transfer-test
  (with-redefs [bank/accounts (ref {"acc1" {:id "acc1" :balance 200}
                                    "acc2" {:id "acc2" :balance 0}})
                audit/audit-log (agent [])]
    (bank/transfer "acc1" "acc2" 100)
    (is (= 100 (:balance (get @bank/accounts "acc1"))))
    (is (= 100 (:balance (get @bank/accounts "acc2"))))))

(deftest concurrent-transfer-test
  (with-redefs [bank/accounts (ref {"acc1" {:id "acc1" :balance 1000}
                                    "acc2" {:id "acc2" :balance 0}})
                audit/audit-log (agent [])]
    (let [futures (doall (repeatedly 10 #(future (bank/transfer "acc1" "acc2" 10))))]
      (doseq [f futures] @f))
    (is (= 900 (:balance (get @bank/accounts "acc1"))))
    (is (= 100 (:balance (get @bank/accounts "acc2"))))))
