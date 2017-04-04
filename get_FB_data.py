# Author: Anthony Abeyta
# Project: Wingman

import urllib2
import json
# commented out for potential use after algorithm is complete
# from gender import getGenders
from datetime import datetime

# @function - get_page_data
# @param page_id - the page parameters for FB api explorer
# @param fields - fields for FB api explorer
# @param access token - FB access token
def get_page_data(page_id, fields,access_token):
    api_endpoint = "https://graph.facebook.com/v2.8/"
    fb_graph_url = api_endpoint + page_id + fields + "&access_token=" + access_token
    try:
        api_request = urllib2.Request(fb_graph_url)
        api_response = urllib2.urlopen(api_request)

        try:
            return json.loads(api_response.read())
        except (ValueError, KeyError, TypeError):
            return "JSON error"

    except IOError, e:
        if hasattr(e, 'code'):
            return e.code
        elif hasattr(e, 'reason'):
            return e.reason

startTime = datetime.now()

fo = open("fb_content.json", "w")

nameList = []

posts_page_id = "me/feed"  # username or id

token = ""  # Access Token
page_data = get_page_data('me/posts', '?fields=id,name,type', token)

postList = []

for i in range(len(page_data['data'])):
    val = page_data['data'][i]['id']
    postList.append(val)

# print(postList)

reactionList = []

# get reaction data for each post
for i in range(len(postList)):
    reaction_page_id = postList[i]
    reaction_data = get_page_data(reaction_page_id + '/reactions', '?fields=id,name,type', token)

    for j in range(len(reaction_data['data'])):
        # print(reaction_data['data'][j]['name'])
        reactionList.append(reaction_data['data'][j])
        # print(str(reaction_page_id) + " " + str(reaction_data['data'][j]))

# get person data for key
person_data = get_page_data('me', '?fields=id,name', token)

# commented out for potential use after algorithm is complete
# newDict = {}
# finalList = []
# for i in range(5):
#
#     print(reactionList[i])
#     name = reactionList[i]['name'].split()
#     firstName = name[0]
#     print(firstName)
#     gender = getGenders(firstName)
#     print(gender[0][0])
#
#     newDict['gender'] = gender[0][0]
#     newDict['name'] = reactionList[i]['name']
#     newDict['id'] = reactionList[i]['id']
#     newDict['type'] = reactionList[i]['type']
#
#     finalList.append(newDict.copy())
    # print(newDict)

# print(finalList)

# print reaction dict output in json format
print(json.dumps({'reactions': reactionList, 'person_id': person_data['id']}))

fo.write(json.dumps({'reactions': reactionList, 'person_id': person_data['id']}))
fo.close()

# print how long program took to run
print datetime.now() - startTime
