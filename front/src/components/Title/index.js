
import 'bootstrap/dist/css/bootstrap.min.css';

export default function Title({ nome }) {

  return (
    <div className='container'>
      <h1>
        {nome}
      </h1>
    </div>
  );
}

