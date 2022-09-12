Vue.component("viewComments",{
    data : function(){
        return{
            comments : null,
            noComments : false,
            acceptance : {acceptComment : null}
        }
    },
    template:
    `
    <div v-if="noComments ">
        <p>Nema komentara za potvrdu</p>
        <button v-on:click="goBack" style="padding: 7px 20px;
    background-color: aqua;">Vrati se</button>
    </div>
    <div v-else>
    <table border="1">
            <tr bgcolor="lightgrey">
                    	    <th>Objekat</th>
                    	    <th>Korisnik</th>
                    	    <th>Komentar</th>
                           	<th>Zvezde</th>
                           	<th>Vidljivost</th>
                           	<th></th>
                           	<th></th>
            </tr>
            <tr v-for="(p, index) in comments" >
                        	<td>{{p.facilityID}}</td>
                        	<td>{{p.customerID}}</td>
                        	<td>{{p.commentText}}</td>
                            <td>{{p.stars}}</td>
                            <td v-if="p.available === 0">Nije vidljiv</td>
                            <td v-else>Vidljiv</td>
                            <td><button v-on:click="accept(p)" style="padding: 7px 20px;
                                    background-color: aqua;">Potvrdi</button></td>
                            <td><button v-on:click="reject(p)" style="padding: 7px 20px;
                                    background-color: aqua;">Odbij</button></td></tr>
            </tr>
        </table>
    <button v-on:click="goBack" style="padding: 7px 20px;
    background-color: aqua;">Vrati se</button>
    </div>
    
    `,
    mounted(){
        axios.get('rest/adminHomePage/viewComments').then(respone => {
            this.comments = respone.data
            this.noComments = false
        })
        .catch(error => {
            this.noComments = true
        })
    },
    methods : {
        goBack : function(){
            router.push('/adminHomePage')
        },
        accept : function(p,event){
            
            axios.post('rest/adminHomePage/viewComments/acceptance',{"id":p.id,"facilityID":p.facilityID,"customerID":p.customerID,
                        "commentText":p.commentText,"stars":p.stars,"available":"1","isDeleted":p.isDeleted})
                        .then(response => {
                            alert("Objekat prihvacen")
                            location.reload()
                    })
            event.preventDefault()
            
        },
        reject : function(p,event){
            axios.post('rest/adminHomePage/viewComments/acceptance',{"id":p.id,"facilityID":p.facilityID,"customerID":p.customerID,
            "commentText":p.commentText,"stars":p.stars,"available":p.available,"isDeleted":"1"})
            .then(response => {
                alert("Objekat nije prihvacen")
                location.reload()
        })
        event.preventDefault()
        }
    }
})