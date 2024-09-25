import styles from './styles.module.css';
import { useRef } from 'react';
import { api } from '../../api/api';
import { useNavigate } from 'react-router-dom';

const Comando = () => {


    
    


    const command = useRef<HTMLInputElement>(null);
    const navigate = useNavigate(); 

    const Post = async (event: React.FormEvent) => {
   

        if (command.current == null) {
            return "Comando invalido";
        }
        if (command.current.value === "start") {
            navigate("/cenas");
         }

        try {
            await api.post("/", {
                comando: command.current.value,
            });
            event.preventDefault();

                
        } catch (error) {
            console.log(error);
            
        }
       
        
    };

    return (
        <form className={styles.comando}>
         
                <input placeholder="Digite o comando" className={styles.input} ref={command} />
                <div className={styles.buttonDiv}>
                    <button type="submit" className={styles.button} onClick={Post}>Enviar</button>
                </div>
            
        </form>
    );
};

export default Comando;
function useState<T>(arg0: null): [any, any] {
    throw new Error('Function not implemented.');
}

