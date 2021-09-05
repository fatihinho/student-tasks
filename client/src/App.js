import './App.css';

import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';

import StudentList from './pages/StudentList';

import AppNavbar from './components/AppNavbar';
import AddStudent from './pages/AddStudent';
import UpdateStudent from './pages/UpdateStudent';
import UploadImage from './pages/UploadImage';

function App() {
  return (
    <div>
      <Router>
        <AppNavbar />
        <Switch>
          <Route path="/" exact={true} component={StudentList} />
          <Route path="/add-student" exact={true} component={AddStudent} />
          <Route path="/update-student/:id" exact={true} component={UpdateStudent} />
          <Route path="/upload-image/:id" exact={true} component={UploadImage} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
