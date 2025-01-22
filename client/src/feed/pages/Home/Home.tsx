import classes from './Home.module.css'
import { Nav } from '../../components/Nav/Nav'
import { News } from '../../components/News/News'
import { Footer } from '../../../authentication/components/Footer/Footer'
export function Home(){
    return (
        <div className={classes.container}>
            <Nav />
            <div className={classes.main}>
                <div className={classes.title}>
                    <h1><span style={{color: 'red'}}>.</span>News</h1>
                </div>
                <News title='Test ' description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum orci justo, rutrum vitae rhoncus non, fermentum vitae massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc risus sem, lobortis eu lacus eget, pharetra ornare mi. Morbi sit amet libero id nulla malesuada pellentesque nec vel nisl.'
                image='https://bip.malopolska.pl/Download/get/id,2902285.json' data='2002.20.22'/><News title='Test ' description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum orci justo, rutrum vitae rhoncus non, fermentum vitae massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc risus sem, lobortis eu lacus eget, pharetra ornare mi. Morbi sit amet libero id nulla malesuada pellentesque nec vel nisl.'
                image='https://bip.malopolska.pl/Download/get/id,2902285.json' data='2002.20.22'/>
                <News title='Test ' description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum orci justo, rutrum vitae rhoncus non, fermentum vitae massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc risus sem, lobortis eu lacus eget, pharetra ornare mi. Morbi sit amet libero id nulla malesuada pellentesque nec vel nisl.'
                image='https://bip.malopolska.pl/Download/get/id,2902285.json' data='2002.20.22'/>
                <News title='Test ' description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum orci justo, rutrum vitae rhoncus non, fermentum vitae massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc risus sem, lobortis eu lacus eget, pharetra ornare mi. Morbi sit amet libero id nulla malesuada pellentesque nec vel nisl.'
                image='https://bip.malopolska.pl/Download/get/id,2902285.json' data='2002.20.22'/>
                <News title='Test ' description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum orci justo, rutrum vitae rhoncus non, fermentum vitae massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc risus sem, lobortis eu lacus eget, pharetra ornare mi. Morbi sit amet libero id nulla malesuada pellentesque nec vel nisl.'
                image='https://bip.malopolska.pl/Download/get/id,2902285.json' data='2002.20.22'/>
                <News title='Test ' description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum orci justo, rutrum vitae rhoncus non, fermentum vitae massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc risus sem, lobortis eu lacus eget, pharetra ornare mi. Morbi sit amet libero id nulla malesuada pellentesque nec vel nisl.'
                image='https://bip.malopolska.pl/Download/get/id,2902285.json' data='2002.20.22'/>
                <News title='Test ' description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum orci justo, rutrum vitae rhoncus non, fermentum vitae massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc risus sem, lobortis eu lacus eget, pharetra ornare mi. Morbi sit amet libero id nulla malesuada pellentesque nec vel nisl.'
                image='https://bip.malopolska.pl/Download/get/id,2902285.json' data='2002.20.22'/>
                <News title='Test ' description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum orci justo, rutrum vitae rhoncus non, fermentum vitae massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc risus sem, lobortis eu lacus eget, pharetra ornare mi. Morbi sit amet libero id nulla malesuada pellentesque nec vel nisl.'
                image='https://bip.malopolska.pl/Download/get/id,2902285.json' data='2002.20.22'/>
                <News title='Test ' description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum orci justo, rutrum vitae rhoncus non, fermentum vitae massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc risus sem, lobortis eu lacus eget, pharetra ornare mi. Morbi sit amet libero id nulla malesuada pellentesque nec vel nisl.'
                image='https://bip.malopolska.pl/Download/get/id,2902285.json' data='2002.20.22'/>
            </div>
            <Footer />
        </div>
    )
}