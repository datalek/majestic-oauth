# Simple project definition

A clients manager, each operation produce an event that describe the action, an action is composed as following:

```
{
    "id": "",
    "subject": {
        "id": "",
        "type": "", // user
    },
    "action": {
        "type": "", // create | update
        "resource": {
            "id": "",
            "type": "", // client
        }
    }
}
```

* A create produce a *create* action type
* An update produce an *update* action type

Each request is protected by authentication, the only valid token is `this-is-the-valid-token`.

The service is multi-tenant, the only valid tenand is `valid-tenant`

## Endpoints

#### POST /clients
Create a new client submitting the following request:
```
POST /clients HTTP/1.1
Host: example.com
Content-Type: application/json
X-Tenant: <tenandId>
Authorization: Bearer xxx

{
    "name": "this is the name",
    "scope": ["read_content", "create_content"]
}

# response
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
    "id": "",
    "name": "",
    "owner": {
        "id": "user"
    }
    "scope": ["read_content", "create_content"]
}
```

#### PUT /clients
Replace a client submitting the following request:
```
PUT /clients/:id HTTP/1.1
Host: example.com
Content-Type: application/json
X-Tenant: <tenandId>
Authorization: Bearer xxx

{
    "name": "this is the name",
    "scope": ["read_content", "create_content"]
}

# response
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
    "id": "",
    "name": "this is the name",
    "owner": {
        "id": "user"
    }
    "scope": ["read_content", "create_content"]
}
```

#### GET /clients/:id
Return the client identified with the given id
```
GET /clients/:id HTTP/1.1
Host: example.com
X-Tenant: <tenandId>
Authorization: Bearer xxx

# response
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
    "id": "",
    "name": "",
    "owner": {
        "id": "user"
    }
    "scope": ["read_content", "create_content"]
}
```

### Generic errors

#### 401
```
HTTP/1.1 401 Unauthorized
WWW-Authenticate: Newauth realm="apps", type=1,
                       title="Login to \"apps\"", Basic realm="simple"
Content-Type: application/json;charset=UTF-8

{
    "code": 4010,
    "message": "Unauthorized"
}
```

#### 403
```
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8

{
    "code": 4030,
    "message": "Forbidden"
}
```

#### 400
```
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
    "code": 4000,
    "message": "Invalid input",
    "errors": [
        {
            "code": 1029,
            "message": "Missing x-tenantid header"
        }
    ]
}
```

#### 500
```
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
    "code": 5000,
    "message": ""
}
```