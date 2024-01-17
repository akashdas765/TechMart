import csv
import pandas as pd
from firebase import firebase





firebase=firebase.FirebaseApplication('https://techmart-67117.firebaseio.com/Products/')
with open('techmart.csv', 'w', newline='') as file:
    fieldnames = ['Year','Month','ProductID','Product Name', 'Price','Brand','Quality(%)','Category','Quantity Sold','Initial Quantity','Quantity Left']
    writer = csv.DictWriter(file, fieldnames=fieldnames)
    writer.writeheader() 
    
    for i in ["11","12","13","14","15","16","21","22","23","24","25","26","31","32","33","34","35","36","41","42","43","44","45","46"]:
        name=firebase.get(i,'Product Name')
        price=firebase.get(i,'Price')
        quality=firebase.get(i,'Quality')
        year=firebase.get(i,'Year')
        month=firebase.get(i,'Month')
        category=firebase.get(i,'Category')
        brand=firebase.get(i,'Brand')
        quantity1=firebase.get(i,'No of items Left')
        quantity=firebase.get(i,'No of items Sold')
        productid=firebase.get(i,'ProductID')
        initial=firebase.get(i,'Initial Products')
        writer.writerow({'Product Name': name, 'Price': price,'Quantity Left':quantity1,'Quantity Sold':quantity,'ProductID':productid,'Initial Quantity':initial,'Quality(%)':quality,'Brand':brand,'Category':category,'Year':year,'Month':month})

pd.read_csv("techmart.csv")