# King's Call

A four-player, two-team trick-taking card game with a short illustrated storyline,
built on the [SaxionApp](resources/SaxionApp.jar) teaching canvas. You partner with
the player across the table and try to be the first side to win **seven sets**.

<p align="center"><i>Menu: <code>1</code> Play &nbsp;·&nbsp; <code>2</code> Tutorial &nbsp;·&nbsp; <code>3</code> Quit</i></p>

---

## Rules

- **Teams.** You are *player 1* (bottom). Your partner is *player 3* (top). The
  opponents are *player 2* (right) and *player 4* (left). Everyone but you is
  played by the computer.
- **Deal.** A standard 52-card deck, 13 cards each. Aces are high.
- **Roles.** Each hand a **king** and a **minister** (a different player) are
  drawn at random.
- **The calls.**
  - The king names a trump suit &mdash; the **king's call**.
  - The minister names a second, different suit &mdash; the **minister's call**.
    It only has trump power on cards the minister plays themselves.
- **Winning a trick.** Highest wins, with this priority:
  1. the highest **king's-call** card, otherwise
  2. the minister's **minister's-call** card &mdash; which beats everything
     *unless the trick was led in the minister's-call suit*, in which case the
     highest card of that suit wins, otherwise
  3. the highest card of the **led suit**.
- **Following suit.** If you hold the led suit you must play it.
- **Scoring.** Each trick won is a **point**. Reaching **7 points** converts to
  one **set**. First team to **7 sets** wins the match. The crown passes to the
  team that just won the set.

## Controls

Everything is keyboard-driven through the SaxionApp console:

| Where | Keys |
|---|---|
| Menu | `1` play · `2` tutorial · `3` quit |
| King's call (you) | `h` `s` `c` `d` |
| Minister's call (you) | `H` `S` `C` `D` |
| Playing a card | the card's number in your hand (`1`–`13`) |
| Story / tutorial slides | any key to advance |

## Running

You need a **JDK 17 or newer** on your `PATH`. Run from the project root so the
relative `resources/` paths resolve.

```bash
# Windows
./run.ps1

# macOS / Linux / Git Bash
./run.sh
```

Or manually:

```bash
javac -cp resources/SaxionApp.jar -d out/production/kingscall $(find BasicGame/src -name '*.java')
java  -cp "out/production/kingscall:resources/SaxionApp.jar" kingscall.KingsCall   # ';' instead of ':' on Windows
```

In IntelliJ IDEA: open the folder, mark `BasicGame/src` as a **Sources Root**, add
`resources/SaxionApp.jar` as a library, and run `kingscall.KingsCall`.

## Project layout

```
BasicGame/src/kingscall/
├── KingsCall.java            Entry point + GameLoop; drives intro → menu → set loop
├── model/                    Plain data, no rendering
│   ├── Suit.java             enum: letter code + icon path
│   ├── Card.java             immutable identity + mutable owner
│   ├── Player.java           a hand + suit-sort helper
│   ├── Seat.java             SOUTH/EAST/NORTH/WEST: ids, partner, play order
│   └── Team.java             HUMAN vs OPPONENT
├── game/                     Rules and flow
│   ├── Deck.java             builds the 52-card deck
│   ├── Dealer.java           deals 13 each
│   ├── RoleSelector.java     random king / minister
│   ├── TrumpCaller.java      king's call & minister's call (human + AI)
│   ├── RoundEngine.java      plays one trick
│   ├── TrickResolver.java    decides the trick winner
│   ├── ScoreBoard.java       points / sets + scoreboard rendering
│   └── ai/OpponentStrategy.java   how a computer opponent picks its card
├── ui/                       Everything that draws
│   ├── Assets.java           every image / sound path
│   ├── TableLayout.java      fixed pixel coordinates for the 1920×1024 table
│   ├── TableRenderer.java    felt, hands, role markers, played cards
│   ├── IntroSequence.java    opening animation
│   └── TutorialSequence.java interactive tutorial
└── audio/
    ├── AudioHelper.java      fire-and-forget sound effects
    └── MusicPlayer.java      looping background music
```

Dependency direction is one-way: `ui` and `game` depend on `model`; `KingsCall`
wires them together. `game` talks to the screen only through `ui`.

## About this refactor

The game was reorganised from 13 classes in the default package into the
packages above. **Behaviour is intentionally unchanged** &mdash; same rules, same
AI, same on-screen sequence, same quirks. The main structural changes:

- Suit handling is a single `Suit` enum instead of `if/else` letter chains
  repeated ~10 times.
- The three byte-for-byte-identical AI opponent blocks in the old `BetterMove`
  are now one routine keyed off `Seat.partner()`.
- The per-leader `switch` arms in the old `Round` collapse to "human vs AI lead"
  and "human vs AI follow".
- The "clear, redraw felt, redraw four hands" block (previously copy-pasted
  about a dozen times) is `TableRenderer`.
- Screen coordinates live in `TableLayout`; asset paths in `Assets`.
- Score keeping is an object (`ScoreBoard`) rather than two `int[2]` arrays.
- Dead code removed (`MPlayer.main`); build output no longer committed.

One deliberate deviation: the old AI "last resort" branch read a list element
*after* removing it, which could throw on a rare edge case. That dead read is
dropped; every non-throwing path behaves identically.
