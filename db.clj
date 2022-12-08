(ns db
  (:require clojure.string))

; Loads data from a file into a vector of vectors (each line will be a vector)
(defn loadData [filename]
  (map #(clojure.string/split % #"\|") (clojure.string/split (slurp filename) #"\n")))

(defn customer-name [customers cust-id]
  (nth (first (filter #(= (first %) cust-id) customers)) 1))

(defn product-description [products prod-id]
  (nth (first (filter #(= (first %) prod-id) products)) 1))

; Functions for #4

(defn find-customer-id-with-name [customers cust-name]
  (first (first (filter #(= (nth % 1) (str cust-name)) customers))))

(defn total-sales-for-customerid [sales products cust-id]
  (let [sales-for-cust (filter #(= (second %) (str cust-id)) sales)]
    (reduce + (map (fn [[_, _, prod-id, count]]
                     (* (Double/parseDouble count) (Double/parseDouble (nth (first (filter #(= (first %) prod-id) products)) 2))))
                   sales-for-cust))))

(defn total-sales-for-customer [sales customers products cust-id]
  (let [cust-id (find-customer-id-with-name customers cust-id)]
    (total-sales-for-customerid sales products cust-id)))

; Functions for #5

(defn get-product-id-from-name [products prod-name]
  (first (first (filter #(= (nth % 1) (str prod-name)) products))))

(defn total-count-for-prod-id [sales prod-id]
  (let [sales-for-prod (filter #(= (nth % 2) (str prod-id)) sales)]
    (reduce + (map read-string (map (fn [[_, _, _, count]] count) sales-for-prod)))))

(defn total-count-for-product [sales products prod-name]
  (let [prod-id (get-product-id-from-name products prod-name)]
    (total-count-for-prod-id sales prod-id)))