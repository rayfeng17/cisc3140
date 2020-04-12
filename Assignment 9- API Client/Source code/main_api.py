import json
import requests

url = "https://the-cocktail-db.p.rapidapi.com/list.php"

querystring = {"c": "list"}

headers = {
    'x-rapidapi-host': "the-cocktail-db.p.rapidapi.com",
    'x-rapidapi-key': "Your API key"
    # "5dc11f6bd4msh47eeca06b2dae52p1b3ef2jsn7da9857fe2f5"
}

response = requests.request("GET", url, headers=headers, params=querystring)

category_list = json.loads(response.text)

print(" Available Cocktail Category : ")
i = 0
for item in (category_list["drinks"]):
    print("   " + str(i) + " " + item['strCategory'])
    i = i + 1

ip_category = input("Enter Category # : ")

print("Category chosen : ", category_list["drinks"][int(ip_category)])
cat_drink = category_list["drinks"][int(ip_category)]
