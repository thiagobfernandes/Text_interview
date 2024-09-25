import styles from '../Game/style.module.css';
import Comando from '../../components/Comando';
import { useState } from 'react';
import { api } from '../../api/api';
import { useEffect } from 'react';


const GameScene = () => {

const [cena, setcena] = useState<any>(null);




const getCena = async () => {
  const cenosFromAPi = await api.get("/");

  return setcena(cenosFromAPi.data)
}

useEffect(() => {
    getCena(); // Carrega as cenas ao montar o componente
}, []);



    return (
        
        <section className={styles.background}>
            <div className={styles.game}>
                <div className={styles.layout}>
                    
                {cena && Array.isArray(cena) && cena.map((scene: any) => {
    return (
        <div key={scene.idInteraction}>
            <p>{scene.descScene}</p>
            <p>{scene.resposta.join()}</p>

        </div>
    );
})}

                  
                </div>
            </div>
            <div className={styles.comando}>
                <Comando/>
            </div>
        </section>

    );
}

export default GameScene;