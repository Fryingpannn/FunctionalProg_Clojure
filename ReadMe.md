# How to run
1. Download docker file
2. Create docker interactive container: `docker container create -i -t --name comp348 --mount type=bind,source="/Users/pan/Desktop/AProgramming/Comp348/ClojureApp/",target=/root/comp348 comp348_image`
3. Run `docker container start -a -i comp348`

This will give you a command line shell inside the Linux distribution. Your source folder from the host system will be
mounted at the target location specified in the command above (e.g., /root/comp348 ).
(Note: Typing "exit" at the command line will terminate your session.)

4. `cd root/comp348`
5. Run `closure app.clj`

# Todo
Notes: 
- Set class path everytime: `export CLASSPATH=./`
- use map, reduce, and filter functions when you can.
- do not use Java for main functionalities, but yes for converting text to numbers.
E.g.: `(map #(Integer/parseInt %) ["6" "2" "3"])`
    - Or use Clojure `(* 4 (read-string “4.5”)) ; = 18`
- Use `slurp` to read text file as string. E.g.: `slurp "myFile.txt"`
- For user input, use `read-line`. 
    - Use `flush` before `read-line` if printed before.
- For string processing, use `clojure.string`. (https://clojuredocs.org/clojure.string)
- Main file called `app.clj` to load data.
    - `menu.clj` for interface.
    - `db.clj` for data management code (loading, organizing).
    - Use namespace for each file. E.g.:
    ```
    (ns app
        (:require [db])
        (:require [menu]))
    ```
1. Load data from 3 disk files
    - Will have 3 databases with different schemas (see below) 
    - No error checking required for data files, each field separated by "|"
    1. Implement db/loadData function to take in e.g. "cust.txt".
2. Display menu: Looped until exit
    1. #1-3: Display is just display table for customer and product
        - For sales, custID needs to be actual name, and prodID needs to be actual product.
    2. #4: Prompt user for customer name
        - Find total value for this customer
        - If customer not valid, indicate 0.00$.
    3. #5: Calculate sales count for a given product.
        - Relate product table with sales table to find count.
        - Show 0 if not found.
    4. #6: exist and display Good Bye.


## Menu
*** Sales Menu ***
------------------
1. Display Customer Table
2. Display Product Table
3. Display Sales Table
4. Total Sales for Customer
5. Total Count for Product
6. Exit
Enter an option?

## Databases:
This shows an example of a file in one of these paths.

Customer table:
<custID,	name,	address,	phoneNumber>
1|John Smith|123 Here Street|456-4567
2|Sue Jones|43 Rose Court Street|345-7867
3|Fan Yuhong|165 Happy Lane|345-4533

Product table
<prodID,	itemDescription,	unitCost>
1|shoes|14.96
2|milk|1.98
3|jam|2.99
4|gum|1.25
5|eggs|2.98
6|jacket|42.99

Sales table
- Indicates custID bought itemCount of product prodID
<salesID,	custID,	prodID,	itemCount>
1|1|1|3
2|2|2|3
3|2|1|1
4|3|3|4

# Functions
## Finding total sales for a given customer
- x = return all instances of customerID in sales table
- Create a map representing product ID and product count
- For each vector in x:
    - Store in map the product ID and product count
- For each vector in the product table:
    - y = return the price times the product count found in map for given product ID.
    - Each of these should be summed up. Use closure function, or add each of the returned value to an array, then sum up array.
- Return final sum.

## Find sales count for a given product
- Get product id from user input.
- For each vector in product table.
    - Check if contains product name given
    - If not, return 0.00$
    - If yes, return product ID.
- With the received product ID, for each vector in sales table:
    - Filter only vectors with the given product ID
        -  For each of these vectors, sum up their product count column.
- Return this final sum.