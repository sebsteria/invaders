package invaders

object UpdateFunctions {
  def updateGrid(gameState: GameState): GameState = {
    // When the grid reaches the edge of the screen it will move down this number of blocks
    val stepDownBlockCount = 4

    // Each time the grid moves it will move this number of blocks left or right
    val stepAcrossBlockCount = 2

    val oldGrid = gameState.alienGrid
    val drawSprite1 = !oldGrid.drawSprite1

    if (gameState.gridDirectionLeft) {
      if (oldGrid.x.v <= 0) {
        gameState.copy(gridDirectionLeft = false, alienGrid = oldGrid.copy(y = oldGrid.y + stepDownBlockCount, drawSprite1 = drawSprite1))
      } else {
        gameState.copy(alienGrid = oldGrid.copy(x = oldGrid.x - stepAcrossBlockCount, drawSprite1 = drawSprite1))
      }
    } else {
      if (oldGrid.x + oldGrid.width >= BlockParty.screenWidth) {
        gameState.copy(gridDirectionLeft = true, alienGrid = oldGrid.copy(y = oldGrid.y + stepDownBlockCount, drawSprite1 = drawSprite1))
      } else {
        gameState.copy(alienGrid = oldGrid.copy(x = oldGrid.x + stepAcrossBlockCount, drawSprite1 = drawSprite1))
      }
    }
  }

  def updateBullet(gameState: GameState): GameState = {
    gameState.bullet.map { b =>
      val newBullet =
        if (b.y.v <= 0) None
        else {
          val newY = b.y - 1
          Some(b.copy(y = newY))
        }

      gameState.copy(bullet = newBullet)
    }.getOrElse(gameState)
  }

  def updateBase(gameState: GameState): GameState = {
    val oldBase = gameState.base

    if (gameState.moveLeft) {
      val newX = 2.max(oldBase.blockX.v - 2)
      val newBase = oldBase.copy(blockX = BlockX(newX))
      gameState.copy(base = newBase)
    } else if (gameState.moveRight) {
      val newX = (oldBase.blockX.v + 2).min(BlockParty.screenWidth.v - oldBase.blockWidth.v - 2)
      val newBase = oldBase.copy(blockX = BlockX(newX))
      gameState.copy(base = newBase)
    } else gameState
  }

}