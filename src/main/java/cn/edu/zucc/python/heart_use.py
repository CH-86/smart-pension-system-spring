import pickle
from sys import argv
import sys
import os

if __name__ == '__main__':
    result = argv[1]
    #result = "63, 1, 3, 145, 233, 1, 0, 150, 0, 2.3, 0, 0, 1"
    string = result.replace(" ", "").split(",")

    values = []
    for s in string:
        values.append(eval(s))

    # print(list)
    # print(type(list))
    values = [values]

    python_dir = 'src\\main\\java\\cn\\edu\\zucc\\python\\'
    f = open(os.path.abspath(python_dir+'heart.pickle'), 'rb')
    models = pickle.load(f)
    f.close()

    # print(values)
    # values = [[63, 1, 3, 145, 233, 1, 0, 150, 0, 2.3, 0, 0, 1]]

    pred_test_y = models.predict_proba(values)
    print(pred_test_y)
