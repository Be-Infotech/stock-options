import axios, {isAxiosError} from 'axios'; // Si vous utilisez ES6

export function loginApi(prmUsername: string, prmPassword: string){
  const data = {
    usernameOrMail : prmUsername,
    password : prmPassword
  }
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
    });

}
