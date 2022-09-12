Vue.component("viewCommentsForFacility",{
    data : function(){
        return{
            comments : null
        }
    },
    template:
    `
    <div>
    <table border="1">
                <tr bgcolor="lightgrey">
                        	    <th>Objekat</th>
                        	    <th>Korisnik</th>
                        	    <th>Komentar</th>
                               	<th>Zvezde</th>
                </tr>
                <tr v-for="(p, index) in comments" >
                            	<td>{{p.facilityID}}</td>
                            	<td>{{p.customerID}}</td>
                            	<td>{{p.commentText}}</td>
                                <td>{{p.stars}}</td>
                </tr>
    </table>
    <td><button v-on:click="goBack" style="padding: 7px 20px;
        background-color: aqua;">Vrati se</button></td></tr>
    </div>
    `,
    mounted(){
        axios.get('rest/customerHomePage/viewCommentsForFacility').then(response => (this.comments = response.data))
    },
    methods : {
        goBack : function(){
            router.push('/customerHomePage')
        }
    }
})