import requests

url = 'http://localhost:8080/lab1_war_exploded/lab1Servlet'
input = {'inWord': 'cherry',
         'inSize': 10}

response_html = requests.post(url, data = input)

response_text = response_html.text.replace("<br>", "\n").replace("<html>", "").replace("</html>", "")\
                                                        .replace("<h2>", "").replace("</h2>", "\n")

print(response_text)
