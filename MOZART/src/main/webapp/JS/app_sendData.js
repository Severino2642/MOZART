// function submitForm() {
//     var formData = new FormData(document.getElementById("myForm"));
//     var xhr = new XMLHttpRequest();
//     xhr.open("POST", "YourServletURL", true);
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState === 4 && xhr.status === 200) {
//             // Traitement de la réponse du serveur si nécessaire
//             console.log(xhr.responseText);
//         }
//     };
//     xhr.send(formData);
// }
// JS -> Servlet
//
// <script>
// function envoyerObjet() {
//     var objet = {
//         propriete1: "valeur1",
//         propriete2: "valeur2"
//         // ... autres propriétés de l'objet
//     };
//
//     var jsonString = JSON.stringify(objet);
//
//     var xhr = new XMLHttpRequest();
//     xhr.open("POST", "VotreServletURL", true);
//     xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState === 4 && xhr.status === 200) {
//             // Traitement de la réponse du serveur si nécessaire
//             console.log(xhr.responseText);
//         }
//     };
//     xhr.send(jsonString);
// }
// </script>
//
//
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// // ... autres importations
//
// @WebServlet("/VotreServletURL")
// public class VotreServlet extends HttpServlet {
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         // Récupérer le flux d'entrée du corps de la requête
//         BufferedReader reader = request.getReader();
//
//         // Lire la chaîne JSON du flux d'entrée
//         StringBuilder jsonString = new StringBuilder();
//         String line;
//         while ((line = reader.readLine()) != null) {
//             jsonString.append(line);
//         }
//
//         // Convertir la chaîne JSON en objet Java en utilisant Jackson (ou tout autre bibliothèque de sérialisation JSON)
//         ObjectMapper objectMapper = new ObjectMapper();
//         VotreObjet objet = objectMapper.readValue(jsonString.toString(), VotreObjet.class);
//
//         // Faire quelque chose avec l'objet (traitement côté serveur)
//         // ...
//
//         // Envoyer une réponse au client si nécessaire
//         PrintWriter out = response.getWriter();
//         out.println("Objet reçu avec succès !");
//         out.close();
//     }
// }
//
//
//
// Servlet -> JS
//
// // @WebServlet("/YourServletURL")
// // public class YourServlet extends HttpServlet {
// //     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// //         // Créer un objet à envoyer
// //         VotreObjet objet = new VotreObjet(); // Remplacez VotreObjet par le type de votre objet
// //
// //         // Convertir l'objet en JSON
// //         Gson gson = new Gson();
// //         String json = gson.toJson(objet);
// //
// //         // Configurer la réponse
// //         response.setContentType("application/json");
// //         response.setCharacterEncoding("UTF-8");
// //
// //         // Envoyer la réponse
// //         response.getWriter().write(json);
// //     }
// // }
//
// {/* <script>
// function fetchData() {
//     var xhr = new XMLHttpRequest();
//     xhr.open("GET", "YourServletURL", true);
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState === 4 && xhr.status === 200) {
//             // Parsez la réponse JSON en objet JavaScript
//             var responseObject = JSON.parse(xhr.responseText);
//
//             // Traitez l'objet comme nécessaire
//             console.log(responseObject);
//         }
//     };
//     xhr.send();
// }
//
// // Appeler la fonction pour récupérer les données lors du chargement de la page, par exemple
// document.addEventListener("DOMContentLoaded", function() {
//     fetchData();
// });
// </script> */}
