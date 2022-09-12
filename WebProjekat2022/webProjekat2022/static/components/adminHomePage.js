Vue.component("adminHomePage",{
    data : function(){
        return{
            adminDTO : null,
            users : null,
        }
    },
    template:`
    <div>
        <div>
            <p>Admin prikeroni {{adminDTO.name}}</p>
            <div>
            <button v-on:click="logout" style="padding: 7px 20px;
            background-color: aqua;">Izloguj se</button><br>
            </div>
            <button v-on:click="registerManager" style="padding: 7px 20px;
            background-color: aqua;">Napravi menadzera</button>
            <button v-on:click="registerCoach" style="padding: 7px 20px;
            background-color: aqua;">Napravi trenera</button>
            <button v-on:click="changeInfoAdmin" style="padding: 7px 20px;
            background-color: aqua;">Izmeni podatke</button>
            <button v-on:click="createFacility" style="padding: 7px 20px;
            background-color: aqua;">Napravi objekat sportski</button>
        </div>
        <div>
            <button v-on:click="viewComment" style="padding: 7px 20px;
            background-color: aqua;">Pregledaj komentare</button>
            <button v-on:click="viewComments" style="padding: 7px 20px;
            background-color: aqua;">Pregledaj sve komentare</button>
        </div>
        <div>
            <button v-on:click="createPromoCode" style="padding: 7px 20px;
            background-color: aqua;">Napravi promo kod</button>
        </div>
        <div>
            <table border="1">
            <tr bgcolor="lightgrey">
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Pol</th>
                    <th>Datum Rodjenja</th>
                    <th>Uloga</th>
                    
                </tr>
            <tr v-for="(p, index) in users" >
                    <td>{{p.name}}</td>
                    <td>{{p.lastName}}</td>
                    <td>{{p.gender}}</td>
                <td>{{p.dateOfBirth}}</td>
                <td>{{p.role}}</td>
                </tr>
            </table>
        </div>
    </div>
    `,mounted(){
        axios.get('/rest/adminHomePage/admin').then(response => (this.adminDTO=response.data));
        axios.get('/rest/adminHomePage/showUsers').then(response => (this.users = response.data));
    },
    methods : {
        registerManager : function(){
            router.push('/adminHomePage/registerManager/');
        },
        registerCoach : function(){
            router.push('/adminHomePage/registerCoach/');
        },
        changeInfoAdmin : function(event){
            router.push('/adminHomePage/changeInfoAdmin/')
        },
        createFacility : function(event){
            router.push('/adminHomePage/createFacility/')
        },
        viewComment : function(event){
            router.push('/adminHomePage/viewComments')
        },
        viewComments : function(event){
            router.push('/adminHomePage/commentsToViewAdmin')
        },
        createPromoCode : function(){
            router.push('/adminHomePage/definePromocode')
        },
        logout : function(event){
            axios.post('rest/adminHomePage/logout').then(response => {
                alert("Izlogovan si")
                router.push('/')
            })

        }
    }
    
}) 