import json
import requests

url = "https://the-cocktail-db.p.rapidapi.com/list.php"

querystring = {"c": "list"}

headers = {
    'x-rapidapi-host': "the-cocktail-db.p.rapidapi.com",
    'x-rapidapi-key': "5dc11f6bd4msh47eeca06b2dae52p1b3ef2jsn7da9857fe2f5"
}

response = requests.request("GET", url, headers=headers, params=querystring)

category_list = json.loads(response.text)

print(" Available Cocktail Category : ")
i = 0
for item in (category_list["drinks"]):
    print("  " + str(i) + " " + item['strCategory'])
    i = i + 1

ip_category = input("Enter Category # : ")

print("Category chosen : ", category_list["drinks"][int(ip_category)])
cat_drink = category_list["drinks"][int(ip_category)]

url = "https://the-cocktail-db.p.rapidapi.com/filter.php"

querystring = {"c": cat_drink["strCategory"]}

headers = {
    'x-rapidapi-host': "the-cocktail-db.p.rapidapi.com",
    'x-rapidapi-key': "5dc11f6bd4msh47eeca06b2dae52p1b3ef2jsn7da9857fe2f5"
}

response = requests.request("GET", url, headers=headers, params=querystring)

drinks_list = json.loads(response.text)
f = open("list.html", "w")
f.write("<HTML><body> <h2> Following are the Drinks for the selected Category " + cat_drink["strCategory"] + "<h2><table>")
print

for drink in drinks_list["drinks"]:
    f.write("<tr><td><img src='" + drink['strDrinkThumb'] + "' alt='" + drink['strDrink'] + "' height='80' "
    "width='80'></td><td><h3>" + drink['strDrink'] + "</h3></td></tr>")

f.write("</table></body></html>")
f.close()

print(" The drink list corresponding to the input is printed in HTML file : list.html")
