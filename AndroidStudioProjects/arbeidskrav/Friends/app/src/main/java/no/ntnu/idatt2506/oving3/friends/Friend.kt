package no.ntnu.idatt2506.oving3.friends


data class Friend(var firstName: String, var lastName: String, var birthDate: String) {
    override fun toString(): String {
        return "$lastName, $firstName. Date of birth: $birthDate"
    }
}
