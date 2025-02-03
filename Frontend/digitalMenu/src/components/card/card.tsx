 /*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: February 02, 2025
 */
 import "./card.css";

 interface CardProps {
     price: number,
     title: string,
     image: string
 }
 
 export function Card({ price, image, title } : CardProps){
     return(
         <div className="card">
             <img src={image}/>
             <h3>{title}</h3>
             <p><b>Preço: </b>R$ {price}</p>
         </div>
     )
 }