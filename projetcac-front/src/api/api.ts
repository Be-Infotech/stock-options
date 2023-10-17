import axios from "axios";


export  function loginApi(username: string, password: string): Promise<any> {
  const url = "http://localhost:8080/user/signin";
  const data = {
    usernameOrMail: username,
    password: password
  }
  return axios.post(url, data)
    .then((response) => {
      return Promise.resolve(response.data);
    })
    .catch((error) => {
      throw error;
    });
}

export function resetPasswordApi(Username: string): Promise<Number | string> {
  const error = 401;
  const password = "1234"

  return Promise.resolve(error)
}


export interface User {
  username: string,
  password: string,
  firstName: string,
  lastName: string,
  mail: string,
  address: string,
  city: string,
  country: string,
  postalCode: string,
  profilePhoto: string
}
