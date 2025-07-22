import java.util.ArrayList;
import java.util.List;

// Observer interface: subscriber who receives updates
interface ISubscriber {
    void update(String videoTitle);
}

// Observable interface: a YouTube channel interface
interface IChannel {
    void subscribe(ISubscriber subscriber);
    void unsubscribe(ISubscriber subscriber);
    void notifySubscribers(String videoTitle);
}

// Concrete Subject: a YouTube channel that observers can subscribe to
class Channel implements IChannel {
    private List<ISubscriber> subscribers; // list of all subscribers
    private String name;

    public Channel(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        // add subscriber only if not already subscribed
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        // remove subscriber
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String videoTitle) {
        // notify each subscriber about the new video
        for (ISubscriber sub : subscribers) {
            sub.update(videoTitle);
        }
    }

    // upload video and notify all subscribers
    public void uploadVideo(String title) {
        System.out.println("\n[" + name + " uploaded \"" + title + "\"]");
        notifySubscribers(title);
    }
}

// Concrete Observer: represents a subscriber to the channel
class Subscriber implements ISubscriber {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println("Hey " + name + ",\nCheckout our new Video : " + videoTitle + "\n");
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        // Create a channel and subscribers
        Channel channel = new Channel("CoderArmy");

        Subscriber subs1 = new Subscriber("Varun");
        Subscriber subs2 = new Subscriber("Tarun");

        // Varun and Tarun subscribe to CoderArmy
        channel.subscribe(subs1);
        channel.subscribe(subs2);

        // Upload a video: both Varun and Tarun are notified
        channel.uploadVideo("Observer Pattern Tutorial");

        // Varun unsubscribes; Tarun remains subscribed
        channel.unsubscribe(subs1);

        // Upload another video: only Tarun is notified
        channel.uploadVideo("Decorator Pattern Tutorial");
    }
}
