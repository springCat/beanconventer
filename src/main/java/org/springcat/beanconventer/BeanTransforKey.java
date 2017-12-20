package org.springcat.beanconventer;

import java.util.Objects;

public class BeanTransforKey {

    private Class source;
    private Class target;

    public BeanTransforKey(Class source, Class target) {
        this.source = source;
        this.target = target;
    }

    public Class getSource() {
        return source;
    }

    public Class getTarget() {
        return target;
    }

    public boolean equals(BeanTransforKey beanTransforKey) {

        if(beanTransforKey == null){
            return false;
        }

        if (this == beanTransforKey) {
            return true;
        }

        return (this.getSource() == beanTransforKey.getSource() && this.getTarget() == beanTransforKey.getTarget());
    }

    public int hashCode(){
        return Objects.hash(this.getSource().hashCode(),this.getTarget().hashCode());
    }
}

