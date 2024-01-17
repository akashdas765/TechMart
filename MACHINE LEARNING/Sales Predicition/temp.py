import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

df = pd.read_csv("Backup.csv")
x = df.iloc[:, 3:8].values
y = df.iloc[:, 8].values

plt.scatter(y,x[:,1])
plt.savefig('figure.png')

from sklearn.preprocessing import OneHotEncoder, LabelEncoder
label_x = LabelEncoder()
x[:, 0] = label_x.fit_transform(x[:, 0])
x[:, 2] = label_x.fit_transform(x[:, 2])
x[:, 4] = label_x.fit_transform(x[:, 4])

onehot1 = OneHotEncoder(categorical_features = [0,2,4])
x = onehot1.fit_transform(x).toarray()

index = int((13/17)*len(df))
x_train, x_test = x[:index], x[index:]
y_train, y_test = y[:index], y[index:]

plt.scatter(x_train[:,17], y_train)

from sklearn.svm import SVC
k = SVC(kernel = 'rbf', random_state = 0)
k.fit(x_train, y_train)

y_pred = k.predict(x_test)

plt.scatter(x_test[:,29], y_pred)