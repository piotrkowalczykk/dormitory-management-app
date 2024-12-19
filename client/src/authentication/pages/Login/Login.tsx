import { Layout } from '../../components/Layout/Layout';
import classes from './Login.module.css';

export function Login(){
    return(
        <Layout>
            <div className={classes.root}>Login</div>
        </Layout>
    );
}