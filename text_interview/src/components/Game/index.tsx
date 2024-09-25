import styles from './style.module.css';
import Comando from '../Comando';



const Game = () => {
    return (
        <>
        <section className={styles.background}>
            <div className={styles.game}>
                <div className={styles.layout}>
                   <h1 className={styles.bemVindo}>Bem vindo Ao Text_interiew</h1>
                   <p className={styles.texto}>  Digite START para iniciar o jogo</p>
                </div>
            </div>
            <div className={styles.comando}>
                <Comando/>
            </div>
        </section>
        </>
    );
}

export default Game;