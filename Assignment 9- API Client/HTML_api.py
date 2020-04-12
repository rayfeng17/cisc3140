import json
import requests
from main_api import cat_drink

url = "https://the-cocktail-db.p.rapidapi.com/filter.php"

querystring = {"c": cat_drink["strCategory"]}

headers = {
    'x-rapidapi-host': "the-cocktail-db.p.rapidapi.com",
    'x-rapidapi-key': "Your API key"
}

response = requests.request("GET", url, headers=headers, params=querystring)

drinks_list = json.loads(response.text)
f = open("list.html", "w")
f.write("<HTML><body> <h2> Following are the Drinks for the selected Category " + cat_drink["strCategory"] + "<h2><table>")
print

for drink in drinks_list["drinks"]:
    f.write("<tr><td><img src='" + drink['strDrinkThumb'] + "' alt='" + drink['strDrink'] + "' height='60' "
    "width='60'></td><td><h3>" + drink['strDrink'] + "</h3></td></tr>")

f.write("</table></body></html>")
f.close()

print(" The drink list corresponding to the input is printed in HTML file : list.html")
