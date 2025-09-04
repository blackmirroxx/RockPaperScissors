# RockPaperScissors
play rock paper scissors, play many games

---
## Code

# Rock-Paper-Scissors in COBOL


```cobol
       IDENTIFICATION DIVISION.
       PROGRAM-ID. RANDOM-NUMBER.
       AUTHOR. "ricardo".
       DATA DIVISION.
       WORKING-STORAGE SECTION.
           01 RAND-NUM PIC 9(2).
           01 CURRENT-TIME.
                  02 T-HOURS PIC 99.
                  02 T-MINS PIC 99.
                  02 T-SECS PIC 99.
                  02 T-MS PIC 999.
           01 PLAYER-CHOICE PIC A(8).
           01 COMPUTER-CHOICE PIC A(10).
           01 CHOICE-IND PIC 9.
           01 BLAH PIC 99.
           01 ROCK PIC A(8) VALUE 'rock'.
           01 SCISSORS PIC A(8) VALUE 'scissors'.
           01 PAPER PIC A(8) VALUE 'paper'.
           01 CHOICES.
                  02 CHOICE PIC A(8) OCCURS 3 TIMES.
           01 RESULT PIC X(20) VALUE 'You lose!'.
       PROCEDURE DIVISION.
           MOVE ROCK TO CHOICE(1).
           MOVE SCISSORS TO CHOICE(2).
           MOVE PAPER TO CHOICE(3).
           ACCEPT current-time FROM TIME.
           DISPLAY 'Pick "rock", "paper", or "scissors"'.
           ACCEPT PLAYER-CHOICE.
           COMPUTE RAND-NUM = FUNCTION RANDOM (T-MS) * 100.
           DIVIDE RAND-NUM BY 3 GIVING BLAH REMAINDER CHOICE-IND.
           MOVE CHOICE(CHOICE-IND + 1) TO COMPUTER-CHOICE.
           DISPLAY 'Computer chose ' COMPUTER-CHOICE.
           IF PLAYER-CHOICE = COMPUTER-CHOICE
                  MOVE 'Tie!' TO RESULT
           END-IF.
           IF PLAYER-CHOICE = 'rock' AND COMPUTER-CHOICE = 'scissors'
                  MOVE 'You win!' TO RESULT
           END-IF.
           IF PLAYER-CHOICE = 'scissors' AND COMPUTER-CHOICE = 'paper'
                  MOVE 'You win!' TO RESULT
           END-IF.
           IF PLAYER-CHOICE = 'paper' AND COMPUTER-CHOICE = 'rock'
                  MOVE 'You win!' TO RESULT
           END-IF.
           DISPLAY RESULT.
       STOP RUN.
```

## Installation Requirements

### Fedora:
```bash
dnf install gnucobol
```

### Gentoo:
```bash
emerge --newuse --deep --ask --backtrack=20 dev-lang/gnucobol
```

## Getting Information

To check the version of `cobc`:
```bash
cobc -V
```

Ensure it's at least version 3.0 or higher.

## Compiling and Execution

To compile:
```bash
cobc -o rock-paper-scissors -x rock-paper-scissors.cob -O2
```

To execute:
```bash
chmod a+x rock-paper-scissors
./rock-paper-scissors
```

Example output:
```
Pick "rock", "paper", or "scissors"
paper
Computer chose rock
You win!
```

## Installation Requirements

### Fedora:
```bash
dnf install gnucobol
```

### Gentoo:
```bash
emerge --new-use --deep --ask --backtrack=20 dev-lang/gnucobol
```

## Getting Information

To check the version of `cobc`:
```bash
cobc -V
```

Ensure it's at least version 3.0 or higher.

## Compiling and Execution

To compile:
```bash
cobc -o rock-paper-scissors -x rock-paper-scissors.cob -O2
```

To execute:
```bash
chmod a+x rock-paper-scissors
./rock-paper-scissors
```

Example output:
```
Pick "rock", "paper", or "scissors"
paper
Computer chose rock
You win!
```
