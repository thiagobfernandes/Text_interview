import logo from '../../imgs/logo-removebg-preview.png';
import styles from './styles.module.css';

const Header = () => {
    return (
        <header>
            <div className={styles.header}>
                <img src={logo} alt="logo-text-interview" />
            </div>
        </header>
    );
}

export default Header;