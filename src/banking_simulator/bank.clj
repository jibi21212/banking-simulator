(ns banking-simulator.bank)

; Purpose: Manage overall bank state, coordinate operations, handle transfers

; Need the following functions:
; open-account
; close-account
; transfer
; get-account

; use STM (dosync ...) to coordinate the updates from accounts
