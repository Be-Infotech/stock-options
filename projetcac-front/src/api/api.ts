export function loginApi(username: string, password: string){

  const data : User = {
    username: "loan",
    password: "loan",
    firstName: "loan",
    lastName: "Heniart",
    mail: "loan@gmail.com",
    address: "1 rue national",
    city: "Bethune",
    country: 'France',
    postalCode: "62400",
    profilePhoto:  ""
  };

  console.log(data)

  return Promise.resolve(data);

  /*
  axios.post('http://localhost:8080/api/auth/signin',data)
    .then((response) => {
      // Gérez la réponse de l'API ici
      console.log('Réponse de l\'API :', response.data);
      return response.data;
    })
    .catch((error) => {
      if (isAxiosError<string>(error))
        if (error.response?.status === 401) {
          return "Erreur 401";
        }
      // Gérez les erreurs ici
      console.error('Erreur lors de la requête API :', error);
      return error;
    });*/

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
