package kebacoinCasino.blackjack.table.card

data class CardImpl(override val type: String, override val value: Int, override val color: String) :
    Card {

    companion object{
        val COLORS:List<String> = arrayListOf("clubs","diamonds","hearts","spades")
        val TYPES:List<String> = arrayListOf("two","three","four","five","six","seven","eight","nine","ten","jack","queen","king","ace")
        val VALUES:List<Int> = arrayListOf(2,3,4,5,6,7,8,9,10,10,10,10,11)
    }

}