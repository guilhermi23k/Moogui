### Moogui

> Welcome to Moogui backend!

---

### Table of Contents

- [Description](#description)
- [How To Use](#how-to-use)
- [References](#references)
- [License](#license)
- [Author Info](#author-info)

---

## Description

This API provides an powerfull way to recommend movies to users. It uses a data-driven recommendation algorithm to generate a list of movies that are relevant to the user's interests. Movies can be recommended based on a variety of criteria, including genre, rating, cast, and crew.

The Movie Recommendation API uses artificial intelligence to read the movies that the user says they like or don't like and recommend movies that they are likely to enjoy. The API is still under development, but it has already movies recomendations with a high degree of accuracy.

If You are looking for Moogui UI, try:
[Moogui-App](https://github.com/guilhermi23k/Moogui-app)

#### Technologies

- Java
- Spring boot
- Spring Security
- Hibernate
- Maven
- AWS RDS (Postgres)


[Back To The Top](#moogui)

---

# How To Use
## API Reference

```http
  Login Controller
```

| Request | METHOD     |  URI | Description | Necessary Role |
| -------- | ------- | ----- | ------------------------- | ---- |
| `POST` | `register` | `http://localhost:8080/register` | Create User Account | NONE
| `GET` | `User` | `http://localhost:8080/user` | Get logged user details | USER


```http
  User Controller
```

| Request | METHOD     |  URI | Description | Necessary Role |
| -------- | ------- | ----- | ------------------------- | ---- |
| `GET` | `users` | `http://localhost:8080/users` | Get all users details | ADMIN
| `GET` | `user/ID` | `http://localhost:8080/user/{id}` | Get user by id | USER
| `PUT` | `user/ID` | `http://localhost:8080/user/{id}` | Update user datiles | USER
| `DELETE` | `user/ID` | `http://localhost:8080/user/{id}` | Delete user | USER
| `GET` | `choices` | `http://localhost:8080/choices` | Get titles recomendaton based on logged user | USER
| `POST` | `favoritedTitle` | `http://localhost:8080/favoritedTitle` | Add new titles to user "Fav titles" and "Fav Genres" | USER


[Back To The Top](#moogui)

---
## Sample API Response for Customer Account Creation 
### Request Type
```
POST
```

### Request URI
```
http://localhost:8080/register
```

### Request Body
```
{
    "username": "guimo",
    "email": "gui.moreirasa@gmail.com",
    "password": "gui196633",
    "role": "ROLE_USER"
}
```

---

## Author Info

If you have projects which you whink i can agregate, contact me :).
- Email - gui.moreirasa@gmail.com
- Linkedin - [Guilherme Moreira](https://www.linkedin.com/in/guim0/)
<!-- - Twitter - [@itsmore1ra](https://twitter.com/itsmore1ra) -->

[Back To The Top](#moogui)
