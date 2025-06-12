(ns banking-simulator.core
  (:gen-class)
  (require [banking-simulator.bank :as bank]
           [banking-simulator.account :as account]
           [banking-simulator.audit :as audit]))

(defn -main
  "The main function which gets everything goin"
  [& args]
  )

; Single account -> Atom
; Multiple accounts -> Ref
; Audit log -> Agent/Channel

; For my use case I am going to have multiple users
; I also want Audit log so I can track what is happening

; Audits will reside in the audit.clj
; Account functionality will reside in the accounts.clj
; The bank design and it's functions will reside in bank.clj
; core.clj will be responsbile for putting everything together