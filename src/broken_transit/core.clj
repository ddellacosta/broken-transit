(ns broken-transit.core
  (:require
   [cognitect.transit :as transit])
  (:import
   [java.io ByteArrayInputStream ByteArrayOutputStream]))

;; more or less verbatim from transit-clj README

(defn -main
  []
  (let [out (ByteArrayOutputStream. 4096)
        writer (transit/writer out :json)]
    (transit/write writer "foo")
    (transit/write writer {:a [1 2]})))

;; in REPL:

;; > (-main)
;; NullPointerException   cognitect.transit/writer/reify--5416 (transit.clj:160)
;; >

;; 1. Caused by java.lang.NullPointerException
;;    (No message)
;; 
;;                transit.clj:  160  cognitect.transit/writer/reify
;;       AbstractEmitter.java:  158  com.cognitect.transit.impl.AbstractEmitter/marshal
;;       AbstractEmitter.java:  211  com.cognitect.transit.impl.AbstractEmitter/marshalTop
;;           JsonEmitter.java:   41  com.cognitect.transit.impl.JsonEmitter/emit
;;         WriterFactory.java:   62  com.cognitect.transit.impl.WriterFactory$1/write
;;                transit.clj:  166  cognitect.transit/write
;;                transit.clj:  163  cognitect.transit/write
;;                   core.clj:   11  broken-transit.core/-main
;;                   core.clj:    7  broken-transit.core/-main
;;                       REPL:    1  broken-transit.core/eval6686
;;                       REPL:    1  broken-transit.core/eval6686
;;              Compiler.java: 6927  clojure.lang.Compiler/eval
;;              Compiler.java: 6890  clojure.lang.Compiler/eval
;;                   core.clj: 3105  clojure.core/eval
;;                   core.clj: 3101  clojure.core/eval
;;                   main.clj:  240  clojure.main/repl/read-eval-print/fn
;;                   main.clj:  240  clojure.main/repl/read-eval-print
;;                   main.clj:  258  clojure.main/repl/fn
;;                   main.clj:  258  clojure.main/repl
;;                   main.clj:  174  clojure.main/repl
;;                RestFn.java: 1523  clojure.lang.RestFn/invoke
;;     interruptible_eval.clj:   87  clojure.tools.nrepl.middleware.interruptible-eval/evaluate/fn
;;                   AFn.java:  152  clojure.lang.AFn/applyToHelper
;;                   AFn.java:  144  clojure.lang.AFn/applyTo
;;                   core.clj:  646  clojure.core/apply
;;                   core.clj: 1881  clojure.core/with-bindings*
;;                   core.clj: 1881  clojure.core/with-bindings*
;;                RestFn.java:  425  clojure.lang.RestFn/invoke
;;     interruptible_eval.clj:   85  clojure.tools.nrepl.middleware.interruptible-eval/evaluate
;;     interruptible_eval.clj:   55  clojure.tools.nrepl.middleware.interruptible-eval/evaluate
;;     interruptible_eval.clj:  222  clojure.tools.nrepl.middleware.interruptible-eval/interruptible-eval/fn/fn
;;     interruptible_eval.clj:  190  clojure.tools.nrepl.middleware.interruptible-eval/run-next/fn
;;                   AFn.java:   22  clojure.lang.AFn/run
;;    ThreadPoolExecutor.java: 1142  java.util.concurrent.ThreadPoolExecutor/runWorker
;;    ThreadPoolExecutor.java:  617  java.util.concurrent.ThreadPoolExecutor$Worker/run
;;                Thread.java:  745  java.lang.Thread/run
