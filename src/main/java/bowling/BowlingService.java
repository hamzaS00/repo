package bowling;

import static java.util.Arrays.stream;

public class BowlingService {

  public int computeScore(String pinsString) {
    int[] pins = parse(pinsString);
    return computeBasicScore(pins) + computeSpareBonus(pins) + computeStrikeBonus(pins);
  }

  private int computeStrikeBonus(int[] pins) {
    int bonus = 0;
    int pinIndex = 0;
    int frameIndex = 0;
    while (frameIndex < 10) {
      if (pins[pinIndex] == 10) {
        bonus += pins[pinIndex + 1] + pins[pinIndex + 2];
        frameIndex++;
        pinIndex++;
      } else if (pins[pinIndex] + pins[pinIndex + 1] == 10) {
        pinIndex += frameIndex < 9 ? 2 : 3;
        frameIndex++;
      } else {
        frameIndex++;
        pinIndex += 2;
      }
    }
    return bonus;
  }

  private int computeSpareBonus(int[] pins) {
    int bonus = 0;
    int pinIndex = 0;
    int frameIndex = 0;
    while (frameIndex < 10) {
      if (pins[pinIndex] == 10) {
        frameIndex++;
        pinIndex++;
      } else if (pins[pinIndex] + pins[pinIndex + 1] == 10) {
        bonus += pins[pinIndex + 2];
        pinIndex += frameIndex < 9 ? 2 : 3;
        frameIndex++;
      } else {
        frameIndex++;
        pinIndex += 2;
      }
    }

    return bonus;
  }

  private int[] parse(String pinsString) {
    var pins = stream(pinsString.split(",")).mapToInt(Integer::parseInt).toArray();
    int pinIndex = 0;
    int frameIndex = 0;
    BowlingFrame firstFrame;
    if (pins[pinIndex] == 10) {
      firstFrame = BowlingFrame.firstStrike();
    } else if (pins[pinIndex] + pins[pinIndex +1] == 10){
      firstFrame = BowlingFrame.firstSpare(pins[pinIndex]);
    } else {
      firstFrame = BowlingFrame.firstBasic(pins[pinIndex], pins[pinIndex+1] );
    }
    BowlingFrame previousFrame = null;

    while (frameIndex < 10) {
      BowlingFrame frame;
      if (pins[pinIndex] == 10) {
        frame = BowlingFrame.strike();
        frameIndex++;
        pinIndex++;
      } else if (pins[pinIndex] + pins[pinIndex + 1] == 10) {
        if (frameIndex < 9) {
          frame = BowlingFrame.spare(pins[pinIndex]);
          pinIndex += 2;
        } else {
          frame = BowlingFrame.lastSpare(pins[pinIndex], pins[pinIndex + 2]);
          pinIndex += 3;
        }
        frameIndex++;
      } else {
        frame = BowlingFrame.basic(pins[pinIndex], pins[pinIndex + 1]);
        frameIndex++;
        pinIndex += 2;
      }
      previousFrame = frame;
    }
    return null;
  }

  private int computeBasicScore(int[] pins) {
    return stream(pins).sum();
  }

  private interface BowlingFrame {

    static BowlingFrame strike() {
      return new StrikeFrame();
    }

    static BowlingFrame spare(int firstThrow) {
      return new SpareFrame(firstThrow);
    }

    static BowlingFrame lastSpare(int firstThrow, int bonusThrow) {
      return new LastSpareFrame(firstThrow, bonusThrow);
    }

    static BowlingFrame basic(int firstThrow, int secondThrow) {
      return new BasicFrame(firstThrow, secondThrow);
    }


    static BowlingFrame firstStrike()
    {
      return new FirstFrameStrike();
    }


    static BowlingFrame firstSpare(int firstThrow)
    {
      return new FirstFrameSpare(firstThrow);
    }


    static BowlingFrame firstBasic(int firstThrow, int secondThrow)
    {
      return new FirstFrameBasic(firstThrow, secondThrow);
    }


    class StrikeFrame implements BowlingFrame {}

    class SpareFrame implements BowlingFrame {
      private int firstThrow;

      public SpareFrame(int firstThrow) {
        this.firstThrow = firstThrow;
      }
    }

    class LastSpareFrame implements BowlingFrame {
      private int firstThrow;
      private int bonusThrow;

      public LastSpareFrame(int firstThrow, int bonusThrow) {
        this.firstThrow = firstThrow;
        this.bonusThrow = bonusThrow;
      }
    }

    class BasicFrame implements BowlingFrame {
      private int firstThrow;
      private int secondThrow;

      public BasicFrame(int firstThrow, int secondThrow) {
        this.firstThrow = firstThrow;
        this.secondThrow = secondThrow;
      }
    }


    class FirstFrameStrike implements BowlingFrame {}

    class FirstFrameSpare implements BowlingFrame {
      private int firstThrow;


      public FirstFrameSpare(int firstThrow) {
        this.firstThrow = firstThrow;
      }
    }

    class FirstFrameBasic implements BowlingFrame {
      private int firstThrow;
      private int secondThrow;

      public FirstFrameBasic(int firstThrow, int secondThrow) {
        this.firstThrow = firstThrow;
        this.secondThrow = secondThrow;
      }
    }
  }
}
