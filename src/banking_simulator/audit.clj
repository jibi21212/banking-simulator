(ns banking-simulator.audit)

(def audit-log (agent []))

(defn log-event [event]
  (send audit-log conj event))