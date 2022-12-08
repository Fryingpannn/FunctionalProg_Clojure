(ns app
  (:require [db])
  (:require [menu])
  (:require clojure.string))

(def customers (db/loadData "cust.txt"))
(def products (db/loadData "prod.txt"))
(def sales (db/loadData "sales.txt"))

(loop []
  (menu/menu)
  (let [option (read-line)]
    (cond
      (= option "1")
      (do
        (println "Customer Table:")
        (flush)
        (doseq [customer customers]
          (println (str customer)))
        (recur))

      (= option "2")
      (do
        (println "Product Table:")
        (flush)
        (doseq [product products]
          (println (str product)))
        (recur))

      (= option "3")
      (do
        (println "Sales Table:")
        (flush)
        (doseq [sale sales]
          (println (str (first sale) ": " (db/customer-name customers (second sale)) " - " (db/product-description products (nth sale 2)) " - " (nth sale 3))))
        (recur))
      
      (= option "sales")
      (do
        (println "Sales Table:")
        (flush)
        (doseq [sale sales]
          (println sale))
      (recur))

          (= option "4")
          (do
            (print "Enter customer name: ")
            (flush)
            (let [cust-name (clojure.string/join (read-line))]
              (println (str "Total sales for customer " cust-name ": " (db/total-sales-for-customer sales customers products (str cust-name)) "$")))
            (recur))

          (= option "5")
          (do
            (print "Enter product name: ")
            (flush)
            (let [prod-name (read-line)]
              (println (str "\nTotal count for product " prod-name ": " (db/total-count-for-product sales products (read-string prod-name)))))
            (recur))

          (= option "6")
          (do
            (println "\nGoodbye!")
            (System/exit 0))

          :else
          (do
            (println "Invalid option. Please try again.")
            (recur))))
      )