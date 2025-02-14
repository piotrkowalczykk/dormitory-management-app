import { Layout } from '../../components/Layout/Layout'
import { Post } from '../../components/Post/Post'
import classes from './Community.module.css'

export function Community(){
    return (
        <Layout>
        <div className={classes.container}>
            <div className={classes.main}>
                <div className={classes.title}>
                    <h1><span style={{color: 'red'}}>.</span>Community</h1>
                </div>
                <Post />
            </div>
        </div>
        </Layout>
    )
}