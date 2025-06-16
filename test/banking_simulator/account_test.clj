(ns banking-simulator.account-test
  (:require [clojure.test :refer :all]
            [banking-simulator.account :as account]))

(deftest create-account-test
  (is (= {:id "acc1" :balance 100}
         (account/create-account "acc1" 100))))

(deftest deposit-test
  (is (= {:id "acc1" :balance 150}
         (account/deposit {:id "acc1" :balance 100} 50))))

(deftest withdraw-test
  (is (= {:id "acc1" :balance 50}
         (account/withdraw {:id "acc1" :balance 100} 50)))
  (is (thrown? Exception
               (account/withdraw {:id "acc1" :balance 50} 100))))
