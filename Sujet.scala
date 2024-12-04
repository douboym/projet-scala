import scala.io.Source

object MowerProject {

  // Fonction pour sécuriser la conversion en entier
  def safeParseInt(str: String): Option[Int] = {
    try {
      Some(str.toInt)
    } catch {
      case _: NumberFormatException => None
    }
  }

  // Classe représentant la tondeuse
  case class Mower(x: Int, y: Int, orientation: Char)

  // Fonction pour traiter les instructions de la tondeuse
  def processMower(mower: Mower, instructions: String, maxX: Int, maxY: Int): Mower = {
    var currentMower = mower
    for (instruction <- instructions) {
      instruction match {
        case 'D' => currentMower = turnRight(currentMower)
        case 'G' => currentMower = turnLeft(currentMower)
        case 'A' => currentMower = moveForward(currentMower, maxX, maxY)
        case _ => println(s"Instruction inconnue : $instruction")
      }
    }
    currentMower
  }

  // Fonction pour tourner la tondeuse à droite
  def turnRight(mower: Mower): Mower = {
    mower.orientation match {
      case 'N' => mower.copy(orientation = 'E')
      case 'E' => mower.copy(orientation = 'S')
      case 'S' => mower.copy(orientation = 'W')
      case 'W' => mower.copy(orientation = 'N')
    }
  }

  // Fonction pour tourner la tondeuse à gauche
  def turnLeft(mower: Mower): Mower = {
    mower.orientation match {
      case 'N' => mower.copy(orientation = 'W')
      case 'E' => mower.copy(orientation = 'N')
      case 'S' => mower.copy(orientation = 'E')
      case 'W' => mower.copy(orientation = 'S')
    }
  }

  // Fonction pour déplacer la tondeuse
  def moveForward(mower: Mower, maxX: Int, maxY: Int): Mower = {
    mower.orientation match {
      case 'N' if mower.y < maxY => mower.copy(y = mower.y + 1)
      case 'S' if mower.y > 0 => mower.copy(y = mower.y - 1)
      case 'E' if mower.x < maxX => mower.copy(x = mower.x + 1)
      case 'W' if mower.x > 0 => mower.copy(x = mower.x - 1)
      case _ => mower // Si la tondeuse sort de la pelouse, elle reste immobile
    }
  }

  // Fonction pour parser les coordonnées et l'orientation
  def parseMowerInput(input: String): Option[Mower] = {
    val parts = input.split(" ")
    if (parts.length == 3) {
      for {
        x <- safeParseInt(parts(0))
        y <- safeParseInt(parts(1))
        orientation = if ("NESW".contains(parts(2))) parts(2).charAt(0) else ' '
        if orientation != ' '
      } yield Mower(x, y, orientation)
    } else {
      None
    }
  }

  // Fonction principale pour lire le fichier et traiter les tondeuses
  def main(args: Array[String]): Unit = {
    val filename = "C:/Users/HP/OneDrive/Bureau/M2 IMSD/Cours/SCALA/Projet/fichier_entree.txt" //fichier d'entrée

    try {
      val lines = Source.fromFile(filename).getLines().toList
      val maxX = safeParseInt(lines.head.split(" ")(0)).getOrElse(0)
      val maxY = safeParseInt(lines.head.split(" ")(1)).getOrElse(0)

      var index = 1
      var mowerCount = 1 // Compteur de tondeuses
      while (index < lines.length) {
        val mowerInput = lines(index)
        val mowerInstructions = lines(index + 1)

        parseMowerInput(mowerInput) match {
          case Some(initialMower) =>
            val finalMower = processMower(initialMower, mowerInstructions, maxX, maxY)
            println(s"Tondeuse $mowerCount : ${finalMower.x} ${finalMower.y} ${finalMower.orientation}")
          case None =>
            println(s"Erreur de format pour la tondeuse : $mowerInput")
        }

        index += 2
        mowerCount += 1 // Incrémenter le numéro de la tondeuse
      }
    } catch {
      case e: Exception => println(s"Erreur lors de la lecture du fichier : ${e.getMessage}")
    }
  }
}
