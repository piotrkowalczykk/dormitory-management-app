import classes from './Dashboard.module.css'
import { Layout } from '../../components/Layout/Layout'
import { Link } from 'react-router-dom'
import { ManageCard } from '../../components/ManageCard/ManageCard'

export function Dashboard(){
    return (
        <Layout pageTitle="Dashboard" navigation={
            <> 
            / <Link to='/dashboard'><span style={{ color: 'red' }}>Dashboard</span></Link>
            </>
        }>
            <div className={classes.container}>
                <ManageCard bgColor='#ff0000' iconName='faNewspaper' counter='1' name='Articles' link='articles' />
                <ManageCard bgColor='#4cff05' iconName='faBuilding' counter='1' name='Dormitories' link='dormitories' />
                <ManageCard bgColor='#0d85fc' iconName='faUsers' counter='1' name='Students' link='students' />
            </div>
        </Layout>
    )
}