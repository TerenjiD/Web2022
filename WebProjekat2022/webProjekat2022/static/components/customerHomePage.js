Vue.component("customerHomePage",{
    data: function() {
        return {
            customer : [],
            facilities: null,
            input: null
        }
    },
    template:`
    <div>
    <div>
                <div>
                    <p>Kupac prikeroni {{customer.name}}</p>
                    <button v-on:click="changeCustomer" style="padding: 7px 20px;
                    background-color: aqua;">Izmeni</button>
                </div>
        		<h3>Prikaz sportskih objekata</h3>
        		</div>
                <div>
                <template>
                    <form>
                    	<label>Pretraga</label>
                    	<input type = "text" v-model = "input" >
                    	<button @click="search" >Pretrazi</button>
                    </form>
                </template>
                </div>
        		<table border="1">
                    <tr bgcolor="lightgrey">
                	    <th>Naziv</th>
                	    <th>Tip</th>
                	    <th>Sadrzaj</th>
                       	<th>Status</th>
                       	<th>Logo</th>
                       	<th>Lokacija</th>
                       	<th>Prosecna ocena</th>
                       	<th>Radno vreme</th>
                	</tr>
                    <tr v-for="(p, index) in facilities" >
                	    <td>{{p.name}}</td>
                	    <td>{{p.facilityType}}</td>
                	    <td>{{p.contentType}}</td>
                        <td>{{p.status}}</td>
                        <td>{{p.logo}}</td>
                        <td>{{p.location.address.street}},{{p.location.address.number}},{{p.location.address.city}},{{p.location.address.country}}</td>
                        <td>{{p.workingHours}}</td>
                        <td>{{p.rating}}</td>
                	</tr>
                </table>
    <button v-on:click = "logoutUser" style="padding: 7px 20px;
    background-color: aqua;" >Log out</button>
    </div>
    `,
    mounted () {
        axios
        .get('rest/facilities/')
        .then(response => (this.facilities = response.data));
        axios.get('/rest/customerHomePage/customer').then(response => (this.customer = response.data))
    },
    methods: {
        logoutUser : function(){
            router.push("/")
        },
        search : function(){
            if(this.input == ""){
                axios
                .get('rest/facilities/')
                .then(response => (this.facilities = response.data))
            }else
                axios
                .get("rest/facilities/search/" + this.input)
                .then(response => (this.facilities = response.data))
        },
        changeCustomer : function(){
            router.push('/changeInfoCustomer/')
        }
    }
})