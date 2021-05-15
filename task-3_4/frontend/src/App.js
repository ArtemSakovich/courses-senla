import logo from './logo.svg';
import './App.css';

function App() {
  return (
      <nav>
        <a className={"leftNavLinks"} href={"/"}>Guest</a>
        <a className={"leftNavLinks"} href={"/"}>Room</a>
        <a className={"leftNavLinks"} href={"/"}>Maintenance</a>
        <a className={"rightNavLinks"} href={"/"}>Sing in</a>
        <a className={"rightNavLinks"} href={"/"}>Sing up</a>
      </nav>
  );
}

export default App;
