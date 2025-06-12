(ns banking-simulator.audit)

; Purpose: Handle audit logging

; Need the following functions here:
; log-event
; get-audit-log

; Should be an agent

(def audit-log (agent []))

(defn log-event [event]
  (send audit-log conj event))