
#curl -X DELETE localhost:9200/_all

#Create our index
curl -X PUT "http://localhost:9200/siteindex" -d '
{
    "mappings": {
        "posts": {
            "properties": {
                "id": {
                    "type": "string", "store":"yes"
                },
                "title": {
                    "type": "string", "store":"yes"
                },
                "content": {
                    "type": "string", "store":"no"
                }
            }
        }
    }
}
'


#Create a documents
curl -X PUT "http://localhost:9200/siteindex/posts/1" -d '
{"title": "title 1", "content": "content 1"}
'

curl -X PUT "http://localhost:9200/siteindex/posts/2" -d '
{"title": "title 2", "content": "content 2"}
'

curl -X PUT "http://localhost:9200/siteindex/posts/3" -d '
{"title": "title 3", "content": "content 3"}
'

curl -X PUT "http://localhost:9200/siteindex/posts/4" -d '
{"title": "title 4", "content": "content 4"}
'

curl -X PUT "http://localhost:9200/siteindex/posts/5" -d '
{"title": "other 1", "content": "other 1"}
'

curl -X PUT "http://localhost:9200/siteindex/posts/6" -d '
{"title": "other 2", "content": "other 2"}
'

#http://localhost:9200/siteindex/posts/_search?pretty=true
#http://localhost:9200/siteindex/posts/_mapping?pretty=true
