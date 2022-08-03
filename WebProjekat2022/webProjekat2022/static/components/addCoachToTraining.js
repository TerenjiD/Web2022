Vue.component("addCoachToTraining",{
    data : function(){
        return{
            coaches : null
        }
    },
    template:
    `
    <div>
        <table>
            <tr>
                <td>Ime sadrzaja:</td>
                <td></td>
            </tr>
            <tr>
                <td><select>

                </select></td>
            </tr>
            <tr>
                <td><button v-on:click="addCoachToContent" style="padding: 7px 20px;
                        background-color: aqua;">Dodaj trenera</button></td>
            </tr>
        </table>
    </div>
    `,
    mounted(){

    },
    methods : {
        addCoachToContent : function(event){
            
        }
    }
})